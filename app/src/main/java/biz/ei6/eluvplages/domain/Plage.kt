package biz.ei6.eluvplages.domain

enum class PlageType { Sable, Galets, Roche }

data class Plage(
    val id: String,
    val nom: String,
    val description: String,
    val type: PlageType ,
    val largeur: Double,
    val longueur: Double,
    val latitude: Double,
    val longitude: Double,
    val lieu : String,
    val url : String,
    val isFavorite: Boolean = false,
    val thumbImageUrl: List<String> = mutableListOf(),
    val featuredImageUrl: String? = null,


) {

    companion object {
        val LISTE = listOf(

            Plage(
                id = "plage_002",
                nom = "Plage de Pampelonne",
                description = "Long ruban de sable mythique, eau claire, ambiance estivale (Ramatuelle).",
                type = PlageType.Sable,
                largeur = 40.0,
                longueur = 4500.0,
                latitude = 43.226149,
                longitude = 6.661677,
                lieu = "Ramatuelle (Var)",
                url = "https://example.com/plages/pampelonne",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_003",
                nom = "Plage de l’Espiguette",
                description = "Immense plage sauvage, dunes et grands espaces (Grau-du-Roi).",
                type = PlageType.Sable,
                largeur = 80.0,
                longueur = 10000.0,
                latitude = 43.483989,
                longitude = 4.138436,
                lieu = "Le Grau-du-Roi (Gard)",
                url = "https://example.com/plages/espiguette",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_004",
                nom = "Plage du Sillon",
                description = "Grande plage urbaine, marées, digue emblématique (Saint-Malo).",
                type = PlageType.Sable,
                largeur = 60.0,
                longueur = 3000.0,
                latitude = 48.65663,
                longitude = -2.01333,
                lieu = "Saint-Malo (Ille-et-Vilaine)",
                url = "https://example.com/plages/sillon",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_005",
                nom = "Plage de l’Almanarre",
                description = "Spot réputé (kite/wind), longue plage avec zones sable/galets (Hyères).",
                type = PlageType.Galets,
                largeur = 30.0,
                longueur = 5000.0,
                latitude = 43.062471,
                longitude = 6.131498,
                lieu = "Hyères (Var)",
                url = "https://example.com/plages/almanarre",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_006",
                nom = "Grande Plage de Biarritz",
                description = "Plage centrale, surf et baignade au pied de la ville (Biarritz).",
                type = PlageType.Sable,
                largeur = 50.0,
                longueur = 700.0,
                latitude = 43.484,
                longitude = -1.55868,
                lieu = "Biarritz (Pyrénées-Atlantiques)",
                url = "https://example.com/plages/biarritz-grande-plage",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_007",
                nom = "Côte des Basques",
                description = "Spot iconique avec vue et ambiance surf, marée très marquée (Biarritz).",
                type = PlageType.Sable,
                largeur = 35.0,
                longueur = 1000.0,
                latitude = 43.4791,
                longitude = -1.55843,
                lieu = "Biarritz (Pyrénées-Atlantiques)",
                url = "https://example.com/plages/cote-des-basques",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_008",
                nom = "Plage de l’Herbe",
                description = "Petite plage côté bassin, eau calme et cabanes ostréicoles (Cap Ferret).",
                type = PlageType.Sable,
                largeur = 25.0,
                longueur = 350.0,
                latitude = 44.6935536,
                longitude = -1.2323217,
                lieu = "L’Herbe, Lège-Cap-Ferret (Gironde)",
                url = "https://example.com/plages/plage-de-l-herbe",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_009",
                nom = "Dune du Pilat (plage)",
                description = "Accès plage au pied de la dune, paysages impressionnants (La Teste-de-Buch).",
                type = PlageType.Sable,
                largeur = 60.0,
                longueur = 2000.0,
                latitude = 44.589652,
                longitude = -1.213334,
                lieu = "La Teste-de-Buch (Gironde)",
                url = "https://example.com/plages/dune-du-pilat",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_010",
                nom = "Plage de La Baule",
                description = "Très grande plage de sable, idéale footing et baignade (Côte d’Amour).",
                type = PlageType.Sable,
                largeur = 70.0,
                longueur = 9000.0,
                latitude = 47.28191,
                longitude = -2.39399,
                lieu = "La Baule-Escoublac (Loire-Atlantique)",
                url = "https://example.com/plages/la-baule",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_011",
                nom = "Plage d’Étretat",
                description = "Galets au pied des falaises, panorama unique (Normandie).",
                type = PlageType.Galets,
                largeur = 30.0,
                longueur = 800.0,
                latitude = 49.70669,
                longitude = 0.20523,
                lieu = "Étretat (Seine-Maritime)",
                url = "https://example.com/plages/etretat",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_012",
                nom = "Plage de Deauville",
                description = "Grande plage aménagée, cabines et ambiance chic (Côte Fleurie).",
                type = PlageType.Sable,
                largeur = 55.0,
                longueur = 1200.0,
                latitude = 49.353976,
                longitude = 0.075122,
                lieu = "Deauville (Calvados)",
                url = "https://example.com/plages/deauville",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_013",
                nom = "Plage de Saleccia",
                description = "Sable blanc et eau turquoise, accès plus sauvage (Agriates, Corse).",
                type = PlageType.Sable,
                largeur = 60.0,
                longueur = 1200.0,
                latitude = 42.729977,
                longitude = 9.207187,
                lieu = "Agriates (Haute-Corse)",
                url = "https://example.com/plages/saleccia",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_014",
                nom = "Plage de Santa Giulia",
                description = "Lagon peu profond, sable fin, très prisée en été (Porto-Vecchio).",
                type = PlageType.Sable,
                largeur = 50.0,
                longueur = 1400.0,
                latitude = 41.5311217,
                longitude = 9.2737175,
                lieu = "Porto-Vecchio (Corse-du-Sud)",
                url = "https://example.com/plages/santa-giulia",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_015",
                nom = "Plage de Palombaggia",
                description = "Pins parasols, rochers et eau limpide, incontournable (Porto-Vecchio).",
                type = PlageType.Sable,
                largeur = 45.0,
                longueur = 1700.0,
                latitude = 41.5579257,
                longitude = 9.3253045,
                lieu = "Porto-Vecchio (Corse-du-Sud)",
                url = "https://example.com/plages/palombaggia",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_016",
                nom = "Plage de l’Ostriconi",
                description = "Plage sauvage entourée de dunes, ambiance nature (Nord Corse).",
                type = PlageType.Sable,
                largeur = 55.0,
                longueur = 900.0,
                latitude = 42.661857,
                longitude = 9.0607371,
                lieu = "Palasca (Haute-Corse)",
                url = "https://example.com/plages/ostriconi",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_017",
                nom = "Plage Notre-Dame",
                description = "Eau limpide, pinède, spot très réputé (Porquerolles).",
                type = PlageType.Sable,
                largeur = 35.0,
                longueur = 500.0,
                latitude = 43.011312,
                longitude = 6.233007,
                lieu = "Île de Porquerolles (Var)",
                url = "https://example.com/plages/notre-dame-porquerolles",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_018",
                nom = "Plages du Prado",
                description = "Grand ensemble de plages, parc du littoral, très accessible (Marseille).",
                type = PlageType.Galets,
                largeur = 40.0,
                longueur = 2500.0,
                latitude = 43.256358,
                longitude = 5.375351,
                lieu = "Marseille (Bouches-du-Rhône)",
                url = "https://example.com/plages/prado",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1526481280695-3c687fd5432c?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_019",
                nom = "Plage de Porticcio",
                description = "Plage familiale face au golfe d’Ajaccio, sable et eau calme selon la météo.",
                type = PlageType.Sable,
                largeur = 45.0,
                longueur = 900.0,
                latitude = 41.8924072,
                longitude = 8.8043257,
                lieu = "Porticcio (Corse-du-Sud)",
                url = "https://example.com/plages/porticcio",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1200&q=80"
            ),

            Plage(
                id = "plage_020",
                nom = "Plage de la Tonnara",
                description = "Plage ventée, très connue pour les sports de glisse (Bonifacio).",
                type = PlageType.Sable,
                largeur = 35.0,
                longueur = 800.0,
                latitude = 41.4259761,
                longitude = 9.1044356,
                lieu = "Bonifacio (Corse-du-Sud)",
                url = "https://example.com/plages/tonnara",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1500375592092-40eb2168fd21?auto=format&fit=crop&w=1200&q=80"
            ),

            // Bonus (si tu veux 21) : Palavas-les-Flots (coord ville/plage)
            Plage(
                id = "plage_021",
                nom = "Plage de Palavas-les-Flots",
                description = "Plage facile d’accès, longue promenade, ambiance station balnéaire.",
                type = PlageType.Sable,
                largeur = 45.0,
                longueur = 3000.0,
                latitude = 43.528906,
                longitude = 3.933493,
                lieu = "Palavas-les-Flots (Hérault)",
                url = "https://example.com/plages/palavas",
                thumbImageUrl = listOf(
                    "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80",
                    "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?auto=format&fit=crop&w=400&q=80"
                ),
                featuredImageUrl = "https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef?auto=format&fit=crop&w=1200&q=80"
            )
        )

        val DEFAULT = Plage(
            id = "plage_001",
            nom = "Plage de la Corniche",
            description = "Grande plage de sable fin idéale pour la baignade et les promenades au coucher du soleil.",
            largeur = 50.0,          // en mètres
            longueur = 1200.0,       // en mètres
            latitude = 43.5528,
            longitude = 7.0215,
            lieu = "Plage de la Corniche",
            type = PlageType.Sable,
            url = "https://example.com/plages/plage-de-la-corniche",
            isFavorite = false,
            thumbImageUrl = listOf(
                "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&q=80",
                "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=400&q=80",
                "https://images.unsplash.com/photo-1493558103817-58b2924bce98?auto=format&fit=crop&w=400&q=80"
            ),
            featuredImageUrl =  "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1200&q=80"
        )
    }
}