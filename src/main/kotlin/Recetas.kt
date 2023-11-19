class Recetas() {
    var recetas = mutableListOf<String>()

    /**
     * Añade una receta a la lista
     */
    fun anadirReceta(nombre:String){
        recetas.add(nombre)
    }

    override fun toString(): String {
        return "Recetas=$recetas"
    }
}