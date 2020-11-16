enum class Module(val id: String, val hasDebugVariant: Boolean = false) {
    DIALOGS("dialogs"),
    LIST_VIEWS("listviews"),
    LOGS("logs", true);

    companion object {
        fun byId(id: String) = values().find { it.id == id }
    }
}