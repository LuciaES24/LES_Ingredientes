class Recetas() {
    var recetas = mutableListOf<String>()

    fun anadirReceta(nombre:String){
        recetas.add(nombre)
    }

    override fun toString(): String {
        return "Recetas=$recetas"
    }


}