

val inventario = mutableListOf<Libro>()
val registroUsuarios = mutableListOf<Usuario>()
val librosPrestados = mutableListOf<ListaLibros>()
fun main() {
    do {
        menu()
        when (readLine()?.toIntOrNull()) {
            1 -> {
                val usuario: Usuario = nuevoUsuario()
                usuario.registrarUsuario(usuario)
            }
            2 -> {
                val libro: Libro = nuevoLibro()
                libro.agregarLibro(libro)
            }
            3 -> {
                val prestamo:Biblioteca = Biblioteca()
                prestamo.prestarLibro()
            }
            4 -> {
                Biblioteca.mostrarLibros()
            }
            5 -> {
                Biblioteca.mostrarUsuarios()
            }
            6 -> {
                Biblioteca.mostrarPrestamos()
            }
            7 -> {
                println("Elegiste salir, hasta la vista!!! ")
                break
            }
            else -> println("Opción no válida. Por favor, elige nuevamente.")
        }
    } while (true)
}

class ListaLibros( val titulo: String, val usuario: String)
class Libro( val titulo: String, val autor: String, val anioPresentacion: Int, var disponible: Boolean){
    fun agregarLibro(libro: Libro){

        inventario.add(libro)
        println("Se agregó con éxito el libro ${libro.titulo}")

    }
}

class Usuario (val nombre: String, val edad: Int){
    fun registrarUsuario(usuario: Usuario) {
        registroUsuarios.add(usuario)
        println("Se agregó con éxito al usuario ${usuario.nombre}")
    }
}

class Biblioteca (){


    companion object{
        fun mostrarUsuarios() {
            println("Nombre\t Edad")
            registroUsuarios.forEach {
                println("${it.nombre} \t ${it.edad} ")
            }
        }

        fun mostrarLibros() {
            println("Título\t Autor\t Año")
            inventario.forEach {
                if (it.disponible) {
                    println("${it.titulo}\t ${it.autor}\t ${it.anioPresentacion}")
                }
            }
        }

        fun mostrarPrestamos() {

            println("Título:\t Usuario: ")

            librosPrestados.forEach {
                    println("${it.titulo}\t ${it.usuario}")
            }
        }
    }


    fun prestarLibro(){
        mostrarLibros()
        println("Ingrese el nombre del libro que desea")
        val book:String = readLine().toString().toUpperCase()
        inventario.forEach(){
            if (it.titulo == book){
                mostrarUsuarios()
                println("Ingrese el usuario que desea el libro")
                val user:String = readLine().toString().toUpperCase()
                registroUsuarios.forEach(){
                    if (it.nombre == user){
                        val lista:ListaLibros = ListaLibros(book, user)
                        librosPrestados.add(lista)
                    }else {
                        println("El usuario no existe")
                    }
                }
                it.disponible = false
            }else {
                println("Libro no existente o no disponible")
            }
        }
    }

}

fun menu(){
    println("Selecciona una opción:")
    println("1. Registrar Usuario")
    println("2. Agregar Libro")
    println("3. Prestar Libro")
    println("4. Ver Libros Disponibles")
    println("5. Ver Usuarios")
    println("6. Ver Préstamos")
    println("7. Salir")
}

fun nuevoUsuario(): Usuario{
    println("Ingrese el nombre del usuario")
    val nombre = readln().toString().toUpperCase()
    println("Ingrese la edad del usuario")
    val edad = readln().toInt()
    var usuario: Usuario = Usuario(nombre,edad)
    return usuario
}

fun nuevoLibro(): Libro{
    println("Ingrese el titulo del libro")
    val titulo = readln().toString().toUpperCase()
    println("Ingrese el autor del libro")
    val autor = readln().toString().toUpperCase()
    println("Ingrese el año de publicación")
    val anio = readln().toInt()
    var libro: Libro = Libro(titulo, autor, anio, disponible = true)
    return libro
}
