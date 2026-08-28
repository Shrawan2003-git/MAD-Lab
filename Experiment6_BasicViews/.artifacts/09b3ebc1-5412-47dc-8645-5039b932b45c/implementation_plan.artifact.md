# Implementation Plan - Fix Build Failure and Refactor Basic Views App

The project is currently failing to build because the `androidx.core` dependencies (version 1.19.0) require a minimum `compileSdk` of 37, while the project is configured with `compileSdk` 36. This plan will fix the build error and perform some essential cleanup.

## Proposed Changes

### [Component: Build Configuration]

#### [MODIFY] [build.gradle.kts](file:///D:/MAD-Lab/Experiment6_BasicViews/app/build.gradle.kts)
- Update `compileSdk` to 37.
- Update `targetSdk` to 37.

### [Component: Android Manifest]

#### [MODIFY] [AndroidManifest.xml](file:///D:/MAD-Lab/Experiment6_BasicViews/app/src/main/AndroidManifest.xml)
- Remove unused `xmlns:tools` namespace declaration to resolve the IDE warning.

### [Component: Resources]

#### [MODIFY] [strings.xml](file:///D:/MAD-Lab/Experiment6_BasicViews/app/src/main/res/values/strings.xml)
- Externalize all hardcoded strings from the layout and activity.

### [Component: UI Layout]

#### [MODIFY] [activity_main.xml](file:///D:/MAD-Lab/Experiment6_BasicViews/app/src/main/res/layout/activity_main.xml)
- Replace hardcoded strings with references to `@string/...`.

### [Component: Main Activity]

#### [MODIFY] [MainActivity.kt](file:///D:/MAD-Lab/Experiment6_BasicViews/app/src/main/java/com/example/experiment6_basicviews/MainActivity.kt)
- Update validation to ensure a course is selected (not the default "Select Course").
- Use string resources with placeholders for the result display instead of string concatenation.
- Externalize Toast messages.

## Verification Plan

### Automated Tests
- Run `./gradlew app:assembleDebug` to verify that the build failure is resolved.

### Manual Verification
- Deploy the app to an emulator (if available).
- Verify that clicking "Submit" with "Select Course" selected shows an error.
- Verify that the result text displays correctly with the new resource-based formatting.
