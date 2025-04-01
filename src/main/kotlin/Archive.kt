data class Archive(
    override val name: String
) : CommonFields {
    val listContent: MutableMap<Int, Note> = mutableMapOf()
}
