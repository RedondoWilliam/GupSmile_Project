package gupsmile.com.model

data class Note(
    /**
     * El identificador de un objeto nota en la base datos se llama Id, a diferencia de realtimedatabase
     * en donde usabamos Key,
     * vamos a obtener un Id que se vaya a asignar cuando construyamos el objeto*/
    var id: String? = null,
    /**
     * es necesario el userId dado que las notas van a estar ligadas a un usario en específico*/
    var userId: String = "",
    val title: String = "",
    val content: String = ""
)