[![API 16](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)]()
[![Download](https://api.bintray.com/packages/kwezal/Kandy/listviews/images/download.svg)](https://bintray.com/kwezal/Kandy/listviews/_latestVersion)
[![Apache License 2.0](https://img.shields.io/badge/License-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Kandy
A **K**otlin library set dedicated to **And**roid platform which will make programming **sweet** again!
### Repository
All modules are available on the jcenter. In order to import them, make sure your project includes the appropriate repository.
#### Project build.gradle
```gradle
repositories {
    // ...
    jcenter()
}
```
## Kandy List Views
Highly flexible list view which will help you to write much less code and spend much less time for such basic thing as list view.
Forget about ~~writing~~ copy-pasting another `RecyclerView.Adapter`.
Let **Kandy List Views** set everything up for you.
Basically, all you need to do is write a custom view holder for each model you want to see as a list item.
### Flexibility
You can use **any** number of **any** types of list items within a single adapter.
`KandyListAdapter` will take care of defining and managing view types.
### Convenience
`onBind` method of a view holder provide you a type-safe getter of a list item.
No need to keep an eye on a collection of data you've passed to the list view adapter.
Let this method retrieve the appropriate object for you (including the inside of event listeners.)
### Minimal working example
#### Custom view holder definition
```kotlin
class StringViewHolder(itemView: View) : AbstractDefaultKandyViewHolder<String>(itemView) {
    private val textView = itemView as TextView
    override fun onBind(position: Int, adapter: KandyListAdapter, listItemGetter: () -> KandyListItem<String>) {
        textView.text = listItemGetter().item
    }
}
```
#### Adapter initialization
```kotlin
val adapter = KandyListAdapter(
    KandyListItem(
        "String item",
        KandyItemView { TextView(this) }
    ) { itemView -> StringViewHolder(itemView) }
)
```
### Full example
For full example, please refer to [this file](https://github.com/Kwezal/Kandy/blob/master/examples/src/main/java/com/kwezal/kandy/ListViewsExampleActivity.kt "ListViewsExampleActivity.kt").
### Import
#### Module build.gradle
```gradle
dependencies {
    // ...
    implementation("com.kwezal.kandy:listviews:${kandy_version}@aar")
            { transitive = true } // Includes RecyclerView dependency
}
```
## Kandy Dialogs
Simple DSL-like wrapper for `AlertDialog`s. Not only it makes the code shorter and _prettier_, but also more convenient to use.
### The simplest example
```kotlin
show { dialog("Message", "Title") { positiveButton("OK") } }
```
### Full example
For full example, please refer to [this file](https://github.com/Kwezal/Kandy/blob/master/examples/src/main/java/com/kwezal/kandy/DialogsExampleActivity.kt "DialogsExampleActivity.kt").
### Import
#### Module build.gradle
```gradle
dependencies {
    // ...
    implementation("com.kwezal.kandy:dialogs:${kandy_version}@aar")
}
```
## The rest
Other modules of this set of libraries will be published soon.
As for now I'd appreciate any kind of feedback or contribution.
