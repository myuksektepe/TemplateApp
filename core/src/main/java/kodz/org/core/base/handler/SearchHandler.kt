package kodz.org.core.base.handler

interface SearchHandler : BaseEventHandler {
    fun searchedText(text: String?)
}