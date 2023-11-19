
class Ingrediente(num:Int, nombre:String, recetas:Recetas) {
    var numero = num
    var nombreIngrediente = nombre
    var recetasIngredente = recetas

    override fun toString(): String {
        return "Ingrediente\nNumero=$numero\nNombre ingrediente='$nombreIngrediente'\nRecetas ingredente=${recetasIngredente}"
    }


}