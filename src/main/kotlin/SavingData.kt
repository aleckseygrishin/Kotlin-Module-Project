import CheckingText.isInt
import Helper.checkEmpty
import java.util.Scanner

object SavingData {
    private val menuText = Menu()
    val listArchive: MutableMap<Int, Archive> = mutableMapOf()
    var nowSelectArchive: Int? = null
    private var nowSelectNote: Int? = null

    fun createArchive() {
        menuText.showTextInMenu(TypeMenu.CREATE_ARCHIVE)
        var name = Scanner(System.`in`).nextLine()

        while(!checkNameIsContains(name, TypeMenu.CREATE_ARCHIVE)) {
            name = Scanner(System.`in`).nextLine()
        }

        listArchive[listArchive.size + 1] = Archive(name)
        println(
            "Архив '$name' создан'."
        )
        return
    }

    fun createNote() {
        menuText.showTextInMenu(TypeMenu.CREATE_NOTE)
        var name = Scanner(System.`in`).nextLine()

        while(!checkNameIsContains(name, TypeMenu.CREATE_NOTE)) {
            name = Scanner(System.`in`).nextLine()
        }
        menuText.showTextInMenu(TypeMenu.CREATE_NOTE_CONTENT)
        var content = Scanner(System.`in`).nextLine()
        while (!checkEmpty(content))
            content = Scanner(System.`in`).nextLine()

        listArchive[nowSelectArchive]!!.listContent[listArchive[nowSelectArchive]!!.listContent.size + 1]= Note(name!!, content!!)
        println(
            "Заметка с именем '$name' и текстом '$content' добалена."
        )
    }

    fun openArchive() {
        if(listArchive.size == 1) {
            nowSelectArchive = 1
            selectOnes(TypeArchOrNotes.ARCHIVE)
        } else {
            listArchive.forEach { (key, value) -> println("Номер архива: $key. Название: ${value.name}") }
            menuText.showTextInMenu(TypeMenu.CHOSE_ARCHIVE_NUMBER)
            var userNumber = Scanner(System.`in`).nextLine()
            while(!checkNumberOfArchiveOrNotes(TypeArchOrNotes.ARCHIVE, userNumber)) {
                userNumber = Scanner(System.`in`).nextLine()
            }
            nowSelectArchive = userNumber.toInt()
            println("Выбран архив номер: $nowSelectArchive")
        }
    }
    fun openNotesInArchive(){
        if(listArchive[nowSelectArchive]!!.listContent.size == 1) {
            nowSelectNote = 1
            selectOnes(TypeArchOrNotes.NOTES)
        } else {
            listArchive[nowSelectArchive]!!.listContent.forEach { (key, value) -> println("Номер заметки: $key. Название: ${value.name}") }
            menuText.showTextInMenu(TypeMenu.CHOSE_NOTE_NUMBER)
            var userNumber = Scanner(System.`in`).nextLine()
            while(!checkNumberOfArchiveOrNotes(TypeArchOrNotes.NOTES, userNumber!!)) {
                userNumber = Scanner(System.`in`).nextLine()
            }
            nowSelectNote = userNumber.toInt()
            println("Выбрана заметка номер: $nowSelectNote")
        }
    }
    fun openNote(){
        val note = listArchive[nowSelectArchive]!!.listContent[nowSelectNote]
        println("Название заметки: ${note!!.name}\nТекст заметки: ${note.content}")
    }

    private fun checkNumberOfArchiveOrNotes(type: TypeArchOrNotes, textNumber: String): Boolean {
        if(!textNumber.isInt()) return false
        if(!checkEmpty(textNumber)) return false
        when(type){
            TypeArchOrNotes.ARCHIVE -> {
                if(listArchive.keys.contains(textNumber.toInt())) return true
                else {
                    println("Архива под таким номером не существует.")
                    return false
                }

            }
            TypeArchOrNotes.NOTES -> {
                if(listArchive[nowSelectArchive]!!.listContent.keys.contains(textNumber.toInt()))
                    return true
                else {
                    println("Заметки под таким номером не существует.")
                    return false
                }
            }
        }
    }

    private fun checkNameIsContains(name: String, type: TypeMenu): Boolean {
        var checking = true
        if(!checkEmpty(name)) checking = false
        else {
            if(type == TypeMenu.CREATE_NOTE) {
                listArchive[nowSelectArchive]!!.listContent.forEach { (_, note) ->
                        if (note.name.contains(name)) {
                            println("Такое имя заметки уже есть!\nВведите другое:")
                            checking = false
                        }
                    }

            } else if(type == TypeMenu.CREATE_ARCHIVE) {
                listArchive.forEach { (_, value) ->
                    if(value.name.contains(name)) {
                        println("Такое имя архива есть!\nВведите другое:")
                        checking = false
                    }
                }
            }
        }
        return checking
    }

    private fun selectOnes(type: TypeArchOrNotes) {
        when(type){
            TypeArchOrNotes.ARCHIVE -> {
                println("Выбран один единственный архив\n" +
                        "Номер: $nowSelectArchive Имя: ${listArchive[nowSelectArchive]!!.name}")
            }

            TypeArchOrNotes.NOTES -> {
                println("Выбрана одна единственная заметка\n" +
                        "Номер: $nowSelectNote Имя: ${listArchive[nowSelectArchive]!!.listContent[nowSelectNote]!!.name}")
            }
        }
    }
}