import Helper.checkEmpty

object CheckingText {
    fun String.isInt(): Boolean {
        when(this.toIntOrNull()) {
            null -> {
                println("Введена не цифра!")
                return false
            }
            else -> return true
        }
    }
    fun checkNumberMenu(type: TypeMenu, text: String): Boolean {
        val numberUnCorrect = "Такой цифры нет в выборе"
        if(!checkEmpty(text)) return false
        if(TypeMenu.MENU_ARCHIVE == type  || TypeMenu.MENU_NOTES == type || TypeMenu.MENU_INSIDE_NOTE == type) {
            if(!text.isInt()) {
                return false
            } else if(
                (text.isInt() && (text.toInt() > 2 || text.toInt() < 0) && TypeMenu.MENU_INSIDE_NOTE != type) ||
                (text.isInt() && (text.toInt() > 2 || text.toInt() < 1) && TypeMenu.MENU_INSIDE_NOTE == type)
                ) {
                println(numberUnCorrect)
                return false
            } else if(
                ((text.isInt() && text.toInt() in 0..2) && TypeMenu.MENU_INSIDE_NOTE != type) ||
                ((text.isInt() && text.toInt() in 1..2) && TypeMenu.MENU_INSIDE_NOTE == type)
                )
                return true
        }
        println("Условия проверок не выполнены")
        return false
    }
}