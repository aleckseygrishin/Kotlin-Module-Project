object Helper {
    fun checkEmpty(name: String): Boolean {
        if(name.trim().isEmpty()) {
            println("Значение не должно быть пустым\nВведите новое:")
            return false
        } else return true
    }
}