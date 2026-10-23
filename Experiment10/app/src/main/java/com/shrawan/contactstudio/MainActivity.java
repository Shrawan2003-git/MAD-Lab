package com.shrawan.contactstudio;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import java.util.*;
import java.util.concurrent.*;

/** Experiment 10: permission-gated access to Android's shared Contacts provider. */
public class MainActivity extends Activity {
 private final int ink=Color.rgb(29,48,39), green=Color.rgb(54,97,75);
 private LinearLayout list; private TextView count, status; private String filter="";
 private final ArrayList<String[]> contacts=new ArrayList<>();
 private final ExecutorService worker=Executors.newSingleThreadExecutor();
 private int generation=0;
 @Override public void onCreate(Bundle b){super.onCreate(b); build();}
 @Override protected void onResume(){super.onResume();load();}
 int dp(int n){return (int)(n*getResources().getDisplayMetrics().density);}
 GradientDrawable bg(int color,int radius){GradientDrawable d=new GradientDrawable();d.setColor(color);d.setCornerRadius(dp(radius));return d;}
 TextView label(String s,int size,int color){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(color);return t;}
 void space(LinearLayout box,int h){View v=new View(this);box.addView(v,new LinearLayout.LayoutParams(1,dp(h)));}
 Button button(String s,Runnable r){Button b=new Button(this);b.setText(s);b.setAllCaps(false);b.setTextColor(Color.WHITE);b.setBackground(bg(green,16));b.setOnClickListener(v->r.run());return b;}
 void build(){
  ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);scroll.setBackgroundColor(Color.rgb(246,245,239));
  LinearLayout root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(24),dp(20),dp(24),dp(24));scroll.addView(root);setContentView(scroll);
  scroll.setOnApplyWindowInsetsListener((v,i)->{root.setPadding(dp(24),dp(20)+i.getSystemWindowInsetTop(),dp(24),dp(24)+i.getSystemWindowInsetBottom());return i;});
  root.addView(label("CONTACT STUDIO     /     EXPERIMENT 10",11,green));space(root,20);
  TextView title=label("Your people.\nOne place.",38,ink);title.setTypeface(null,Typeface.BOLD);root.addView(title);space(root,12);
  root.addView(label("A little closer, through shared data.",15,green));space(root,24);
  LinearLayout card=new LinearLayout(this);card.setOrientation(1);card.setPadding(dp(20),dp(18),dp(20),dp(18));card.setBackground(bg(ink,22));
  card.addView(label("BUILT BY",10,Color.rgb(184,210,185)));space(card,7);card.addView(label("Shrawan Gautam",22,Color.WHITE));card.addView(label("USN  •  25MCAR0229",13,Color.rgb(221,230,210)));root.addView(card);space(root,22);
  count=label("Your contacts",23,ink);count.setTypeface(null,Typeface.BOLD);root.addView(count);space(root,5);
  status=label("",13,green);root.addView(status);space(root,14);
  EditText search=new EditText(this);search.setSingleLine();search.setTextSize(15);search.setHint("Search a name or number");search.setPadding(dp(16),dp(12),dp(16),dp(12));search.setBackground(bg(Color.WHITE,14));root.addView(search);space(root,14);
  search.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int a,int c,int f){}public void onTextChanged(CharSequence s,int a,int before,int c){filter=s.toString();render();}public void afterTextChanged(Editable e){}});
  root.addView(button("Connect / refresh contacts",()->{if(checkSelfPermission(Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED)load();else if(!shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)&&getPreferences(0).getBoolean("asked",false)){startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,android.net.Uri.parse("package:"+getPackageName())));}else{getPreferences(0).edit().putBoolean("asked",true).apply();requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},10);}}));space(root,10);
  root.addView(button("Add a contact",()->{Intent intent=new Intent(Intent.ACTION_INSERT,ContactsContract.Contacts.CONTENT_URI);intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);try{startActivity(intent);}catch(android.content.ActivityNotFoundException e){Toast.makeText(this,"Install a Contacts app to add contacts",Toast.LENGTH_LONG).show();}}));space(root,18);
  list=new LinearLayout(this);list.setOrientation(1);root.addView(list);space(root,20);root.addView(label("ANDROID CONTACTS PROVIDER\nShared securely. Permission first. Stored on your device.",11,green));
 }
 void load(){final int ticket=++generation;if(checkSelfPermission(Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){contacts.clear();status.setText("Permission required • you control access");render();return;}status.setText("Reading your device contacts…");worker.execute(()->{
  ArrayList<String[]> rows=new ArrayList<>();String error=null;
  try(Cursor c=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC")){
   if(c!=null)while(c.moveToNext()){rows.add(new String[]{c.isNull(0)?"Unnamed contact":c.getString(0),c.isNull(1)?"":c.getString(1)});}
  }catch(Exception e){error="Unable to read contacts. Check permission and retry.";}
  final String message=error;runOnUiThread(()->{if(isDestroyed()||ticket!=generation)return;contacts.clear();contacts.addAll(rows);status.setText(message==null?"Live from your device • phone entries":message);render();});
 });}
 void render(){if(list==null)return;list.removeAllViews();int shown=0;for(String[] entry:contacts){if(!(entry[0]+" "+entry[1]).toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))continue;shown++;
  LinearLayout card=new LinearLayout(this);card.setOrientation(1);card.setPadding(dp(18),dp(16),dp(18),dp(16));card.setBackground(bg(Color.WHITE,18));
  TextView name=label(entry[0],19,ink);name.setTypeface(null,Typeface.BOLD);card.addView(name);space(card,4);card.addView(label(entry[1],15,green));space(card,8);
  TextView share=label("Share contact  ↗",13,green);share.setPadding(0,dp(10),0,dp(10));share.setOnClickListener(v->{Intent send=new Intent(Intent.ACTION_SEND);send.setType("text/plain");send.putExtra(Intent.EXTRA_TEXT,entry[0]+"\n"+entry[1]);startActivity(Intent.createChooser(send,"Share contact"));});card.addView(share);list.addView(card);space(list,10);
 }count.setText("Your contacts  /  "+shown);if(shown==0){TextView empty=label(checkSelfPermission(Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED?"Your address book stays private.\nTap Connect to allow read access.":contacts.isEmpty()?"A fresh start.\nAdd a phone contact, then return here.":"No matches. Try another name or number.",16,green);empty.setPadding(dp(12),dp(20),dp(12),dp(20));list.addView(empty);}}
 @Override public void onRequestPermissionsResult(int r,String[] p,int[] g){super.onRequestPermissionsResult(r,p,g);load();}
 @Override protected void onDestroy(){generation++;worker.shutdownNow();super.onDestroy();}
}
