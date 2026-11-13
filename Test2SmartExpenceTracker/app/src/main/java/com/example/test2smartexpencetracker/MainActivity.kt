package com.example.test2smartexpencetracker

import android.Manifest
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2smartexpencetracker.data.DatabaseHelper
import com.example.test2smartexpencetracker.receiver.ReminderManager
import com.example.test2smartexpencetracker.receiver.ReminderReceiver
import com.example.test2smartexpencetracker.ui.BalanceAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var reminderManager: ReminderManager
    private lateinit var balanceAdapter: BalanceAdapter

    private lateinit var tvHeroBalance: TextView
    private lateinit var tvHeroLastUpdated: TextView
    private lateinit var tvHeroTotalEntries: TextView
    private lateinit var switchReminder: SwitchMaterial
    private lateinit var tvReminderTime: TextView
    private lateinit var btnSetReminderTime: MaterialButton
    private lateinit var cardBalanceEntry: MaterialCardView
    private lateinit var etBalanceAmount: TextInputEditText
    private lateinit var etBalanceNote: TextInputEditText
    private lateinit var btnSaveBalance: MaterialButton
    private lateinit var rvBalanceHistory: RecyclerView
    private lateinit var tvEmptyState: TextView

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.permission_required), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DatabaseHelper(this)
        reminderManager = ReminderManager(this)

        initViews()
        setupListeners()
        setupRecyclerView()
        checkNotificationPermission()
        updateReminderUI()
        loadDashboardData()

        handleNotificationIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent(intent)
    }

    private fun initViews() {
        tvHeroBalance = findViewById(R.id.tvHeroBalance)
        tvHeroLastUpdated = findViewById(R.id.tvHeroLastUpdated)
        tvHeroTotalEntries = findViewById(R.id.tvHeroTotalEntries)
        switchReminder = findViewById(R.id.switchReminder)
        tvReminderTime = findViewById(R.id.tvReminderTime)
        btnSetReminderTime = findViewById(R.id.btnSetReminderTime)
        cardBalanceEntry = findViewById(R.id.cardBalanceEntry)
        etBalanceAmount = findViewById(R.id.etBalanceAmount)
        etBalanceNote = findViewById(R.id.etBalanceNote)
        btnSaveBalance = findViewById(R.id.btnSaveBalance)
        rvBalanceHistory = findViewById(R.id.rvBalanceHistory)
        tvEmptyState = findViewById(R.id.tvEmptyState)
    }

    private fun setupListeners() {
        // Toggle Reminder Switch
        switchReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reminderManager.scheduleDailyReminder()
                Toast.makeText(this, getString(R.string.reminder_updated_success), Toast.LENGTH_SHORT).show()
            } else {
                reminderManager.cancelReminder()
                Toast.makeText(this, "Daily reminder disabled", Toast.LENGTH_SHORT).show()
            }
            updateReminderUI()
        }

        // Set Reminder Time Button
        btnSetReminderTime.setOnClickListener {
            showTimePickerDialog()
        }

        // Save Balance Button
        btnSaveBalance.setOnClickListener {
            saveBalanceEntry()
        }
    }

    private fun setupRecyclerView() {
        balanceAdapter = BalanceAdapter(emptyList()) { record ->
            dbHelper.deleteBalanceRecord(record.id)
            loadDashboardData()
            Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show()
        }
        rvBalanceHistory.layoutManager = LinearLayoutManager(this)
        rvBalanceHistory.adapter = balanceAdapter
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun showTimePickerDialog() {
        val currentHour = reminderManager.reminderHour
        val currentMinute = reminderManager.reminderMinute

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                reminderManager.scheduleDailyReminder(hourOfDay, minute)
                switchReminder.isChecked = true
                updateReminderUI()
                Toast.makeText(this, getString(R.string.reminder_updated_success), Toast.LENGTH_SHORT).show()
            },
            currentHour,
            currentMinute,
            false
        )
        timePickerDialog.show()
    }

    private fun updateReminderUI() {
        switchReminder.isChecked = reminderManager.isReminderEnabled
        val hour = reminderManager.reminderHour
        val minute = reminderManager.reminderMinute

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val formattedTime = sdf.format(calendar.time)

        tvReminderTime.text = "Reminder Time: $formattedTime"
    }

    private fun saveBalanceEntry() {
        val amountStr = etBalanceAmount.text?.toString()?.trim()
        val noteStr = etBalanceNote.text?.toString()?.trim() ?: ""

        val balanceAmount = amountStr?.toDoubleOrNull()
        if (balanceAmount == null || balanceAmount < 0) {
            etBalanceAmount.error = getString(R.string.invalid_balance_error)
            etBalanceAmount.requestFocus()
            return
        }

        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        val currentTimestamp = sdf.format(Date())

        // 1. Insert into SQLite database
        dbHelper.insertBalanceRecord(balanceAmount, currentTimestamp, noteStr)

        // 2. Stop/clear active reminder notification (Requirement 4)
        ReminderReceiver.cancelNotification(this)

        // 3. Reschedule next daily reminder (Requirement 4)
        if (reminderManager.isReminderEnabled) {
            reminderManager.scheduleDailyReminder()
        }

        // 4. UI feedback & reset input
        etBalanceAmount.text?.clear()
        etBalanceNote.text?.clear()
        etBalanceAmount.error = null

        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(etBalanceAmount.windowToken, 0)

        Snackbar.make(findViewById(R.id.main), getString(R.string.balance_saved_success), Snackbar.LENGTH_LONG).show()

        // 5. Refresh records & hero summary
        loadDashboardData()
    }

    private fun loadDashboardData() {
        val records = dbHelper.getAllBalanceRecords()
        balanceAdapter.updateRecords(records)

        if (records.isEmpty()) {
            tvEmptyState.visibility = View.VISIBLE
            rvBalanceHistory.visibility = View.GONE
            tvHeroBalance.text = "$ 0.00"
            tvHeroLastUpdated.text = "Last updated: No records yet"
            tvHeroTotalEntries.text = "0 Entries"
        } else {
            tvEmptyState.visibility = View.GONE
            rvBalanceHistory.visibility = View.VISIBLE

            val latest = records.first()
            val formatter = NumberFormat.getCurrencyInstance(Locale.US)
            tvHeroBalance.text = formatter.format(latest.balance)
            tvHeroLastUpdated.text = "Last updated: ${latest.timestamp}"
            tvHeroTotalEntries.text = "${records.size} ${if (records.size == 1) "Entry" else "Entries"}"
        }
    }

    private fun handleNotificationIntent(intent: Intent?) {
        if (intent?.getBooleanExtra(ReminderReceiver.EXTRA_FROM_NOTIFICATION, false) == true) {
            etBalanceAmount.requestFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(etBalanceAmount, InputMethodManager.SHOW_IMPLICIT)

            Snackbar.make(
                findViewById(R.id.main),
                "Update your remaining balance below 👇",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
