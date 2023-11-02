package gupsmile.com.model

data class Contact(
    /**
     * Key Referencia a un contacto dentro de la base de datos, con ella mapeamos el contacto dentro
     * de la base de datos, está como nulo dado que no queremos obtener el contacto en sí sino todo
     * el json de los contactos, mapeando cada objeto  obtener el key de cada contacto
     * */
    val key: String? = null,
    val name: String = "",
    val lastName: String = "",
    val address: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    /**
     * guarda el uid del ususario que se encuentra logeado en ese momento
     * */
    val uid: String = ""
)