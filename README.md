# viewpager-dialog

Helper to construct a standard Android dialog, containing a viewager to display a list of messages.

**Gradle**

compile 'org.neidhardt:viewpager-dialog:1.0.3'

**Usage**
```kotlin
val dialog = ViewPagerDialog()

val messagesToDisplay = ArrayList<String>()
messagesToDisplay.add("Text_1")
messagesToDisplay.add("Text_2")
messagesToDisplay.add("Text_3")

dialog.setTitle("ViewPagerDialog (optional)")
dialog.setMessage("This is also optional")
dialog.setPositiveButtonLabel("ok")
dialog.setViewData("cancel")

dialog.show(this.supportFragmentManager, "DialogFragmentTag")
```