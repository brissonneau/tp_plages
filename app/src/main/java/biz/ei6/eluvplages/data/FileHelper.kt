package biz.ei6.eluvplages.data

import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.domain.PlageType

fun Plage.toStorageString(): String =
    listOf(
        id,
        nom,
        description,
        type.name,
        largeur.toString(),
        longueur.toString(),
        latitude.toString(),
        longitude.toString(),
        lieu,
        url,
        isFavorite.toString(),
        thumbImageUrl.joinToString(","),
        featuredImageUrl.orEmpty()
    ).joinToString("|")


fun String.toPlage(): Plage {
    val parts = split("|")

    require(parts.size >= 13) { "Format Plage invalide" }

    return Plage(
        id = parts[0],
        nom = parts[1],
        description = parts[2],
        type = PlageType.valueOf(parts[3]),
        largeur = parts[4].toDouble(),
        longueur = parts[5].toDouble(),
        latitude = parts[6].toDouble(),
        longitude = parts[7].toDouble(),
        lieu = parts[8],
        url = parts[9],
        isFavorite = parts[10].toBoolean(),
        thumbImageUrl = if (parts[11].isBlank())
            emptyList()
        else
            parts[11].split(","),
        featuredImageUrl = parts[12].ifBlank { null }
    )
}
