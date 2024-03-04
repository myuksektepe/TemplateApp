package kodz.org.core.domain.interfaces.handler

interface SearchHandler : BaseEventHandler {
    fun searchedText(text: String?)
}