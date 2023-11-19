
class Ingrediente(num:Int, nombre:String, recetas:Recetas) {
    var numero = num
    var nombreIngrediente = nombre
    var recetasIngredente = recetas

    override fun toString(): String {
        var cadena = "Ingrediente\nNumero=$numero\nNombre ingrediente='$nombreIngrediente'\nRecetas="
        for (receta in recetasIngredente.recetas){
            cadena += receta
        }
        return cadena
    }


}