import CheckingText.checkNumberMenu
import SavingData.createArchive
import SavingData.createNote
import SavingData.listArchive
import SavingData.nowSelectArchive
import SavingData.openArchive
import SavingData.openNote
import SavingData.openNotesInArchive
import java.util.Scanner

class Menu {
    private val commonTextChose = "Введите номер"
    private val choseTextArchive = "$commonTextChose архива"
    private val choseTextNote = "$commonTextChose заметки"
    private val commonTextCreate = "Введите название"
    private val archiveNameCreate = "$commonTextCreate архива"
    private val noteNameCreate = "$commonTextCreate заметки"
    private val noteContentCreate = "Введите текст заметки"
    private val exitText = "Вы завершили выполнение программы."
    private var scanner = Scanner(System.`in`)
    private var userCommand: String = ""

    private val menuArchive = mapOf(
        CREATE_NUMBER to "Создать архив",
        SELECT_NUMBER to "Выбрать архив",
        EXIT to "Завершить программу"
    )
    private val menuNote = mapOf(
        CREATE_NUMBER to "Создать заметку",
        SELECT_NUMBER to "Выбрать заметку",
        EXIT to "Назад"
    )
    private val menuInsideNote = mapOf(
        SELECT_NUMBER to "Открыть заметку",
        EXIT to "Назад"
    )
    fun helloScreen() {
        println("Добро пожаловать в Архив!!!")
    }

    fun showTextInMenu(type: TypeMenu) {
        when(type) {
            TypeMenu.MENU_ARCHIVE -> menuArchive.forEach { (key, value) -> println("$key. $value") }
            TypeMenu.CREATE_ARCHIVE -> println(archiveNameCreate)
            TypeMenu.MENU_NOTES -> menuNote.forEach { (key, value) -> println("$key. $value") }
            TypeMenu.CREATE_NOTE -> println(noteNameCreate)
            TypeMenu.CREATE_NOTE_CONTENT -> println(noteContentCreate)
            TypeMenu.MENU_INSIDE_NOTE -> menuInsideNote.forEach {(key,value) -> println("$key. $value") }
            TypeMenu.CHOSE_ARCHIVE_NUMBER -> println(choseTextArchive)
            TypeMenu.CHOSE_NOTE_NUMBER -> println(choseTextNote)
            TypeMenu.EXIT_TEXT -> println(exitText)
        }
    }
    fun archiveMenu() {
        while(true) {
            showTextInMenu(TypeMenu.MENU_ARCHIVE)
            userCommand = scanner.nextLine()
            while(!checkNumberMenu(TypeMenu.MENU_ARCHIVE, userCommand)) {
                userCommand = scanner.nextLine()
            }
            when(userCommand.toInt()) {
                0 -> createArchive()
                1 -> {
                    if(listArchive.isEmpty()) println("Список архивов пустой")
                    else notesMenu()
                }
                2 -> {
                    showTextInMenu(TypeMenu.EXIT_TEXT)
                    break
                }
            }
        }
    }
    private fun notesMenu() {
        openArchive()
        while(true) {
            showTextInMenu(TypeMenu.MENU_NOTES)
            userCommand = scanner.nextLine()
            while(!checkNumberMenu(TypeMenu.MENU_NOTES, userCommand))
                userCommand = scanner.nextLine()
            when(userCommand.toInt()) {
                0 -> createNote()
                1 -> {
                    if(listArchive[nowSelectArchive]!!.listContent.isEmpty())
                        println("В архиве нет заметок")
                    else noteInsideMenu()
                }
                2 -> {
                    nowSelectArchive = null
                    return
                }
            }
        }
    }

    private fun noteInsideMenu() {
        openNotesInArchive()
        while(true) {
            showTextInMenu(TypeMenu.MENU_INSIDE_NOTE)
            userCommand = scanner.nextLine()
            while(!checkNumberMenu(TypeMenu.MENU_INSIDE_NOTE, userCommand))
                userCommand = scanner.nextLine()
            when(userCommand.toInt()) {
                1 -> openNote()
                2 -> return
            }
        }
    }

    companion object {
        private const val CREATE_NUMBER = 0
        private const val SELECT_NUMBER = 1
        private const val EXIT = 2
    }

}

enum class TypeMenu {
    MENU_NOTES,
    MENU_ARCHIVE,
    CREATE_NOTE,
    CREATE_NOTE_CONTENT,
    CREATE_ARCHIVE,
    MENU_INSIDE_NOTE,
    CHOSE_ARCHIVE_NUMBER,
    CHOSE_NOTE_NUMBER,
    EXIT_TEXT
}

enum class TypeArchOrNotes {
    ARCHIVE,
    NOTES
}