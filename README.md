# GlassLock Studio

A small Android app that previews and customizes a Liquid Glass-inspired lock-screen design.

## Features in 0.1.0
- Three built-in looks: Aurora, Midnight, Rose Ice.
- Clock/date preview, demo weather and music cards.
- Toggles for weather, music, and 24-hour clock.
- Preferences saved on-device.
- Runs offline; no account, analytics, or network permission.

## Security boundary
GlassLock Studio is a **theme preview/customization app**, not a secure replacement for Android's system lock screen. It deliberately does not disable Android Keyguard, capture credentials, or request accessibility/overlay privileges. Keep the Vivo system PIN/password/biometrics enabled.

## Build
GitHub Actions builds a debug APK on every push to `main`. The APK is uploaded as a workflow artifact and to the prerelease named `nightly`.
