package org.manumatnez.recursosexamenpis.db

import java.io.Serializable

const val NAME = "name"
const val MAIL = "mail"

class User : Serializable {
    var name: String? = null
    var mail: String? = null

    constructor() {
    }

    constructor(
        name: String,
        mail: String
    ) {
        this.name = name
        this.mail = mail
    }
}