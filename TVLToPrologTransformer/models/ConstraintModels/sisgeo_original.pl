sisgeo(Features, Attr):-

Features = [Sisgeo, Ubicacion, Mapa, Capas, Zonas, Relieve, Satelite, Ruta, Mallavial, Aplicaciones, Puntosinteres, Trafico, Indicaciones, Visual, Auditivo, Vibratorio, Tramovial],
Attr = [Coordenadas, Precision, Mediostransporte, Opcionesruta],

fd_domain([Sisgeo, Ubicacion, Mapa, Capas, Zonas, Relieve, Satelite, Ruta, Mallavial, Aplicaciones, Puntosinteres, Trafico, Indicaciones, Visual, Auditivo,Vibratorio,Tramovial], 0, 1),
fd_domain([Coordenadas], 1, 2),
fd_domain([Precision], 1, 7),
fd_domain([Mediostransporte],1,5),
fd_domain([Opcionesruta],1,6),

Sisgeo #= 1,
Sisgeo #= Ubicacion,
Sisgeo #>= Mapa,

Ubicacion #> 0 #<=> Precision #> 0,
Ubicacion #> 0 #<=> Coordenadas #> 0,

Mapa #= Capas,
Mapa #>= Zonas,

Capas #>= Relieve,
Capas #>= Satelite,
Capas #>= Ruta,
Capas #>= Mallavial,
Capas #>= Aplicaciones,
Capas #>= Puntosinteres,
Capas #>= Trafico,

Capas * 1 #=< Relieve + Satelite + Ruta + Mallavial + Aplicaciones + Puntosinteres + Trafico,
Relieve + Satelite + Ruta + Mallavial + Aplicaciones + Puntosinteres + Trafico #=< Capas * 6,
Relieve + Satelite #=< 1,
Mediostransporte #> 0 #<=> Ruta #> 0,
Opcionesruta #> 0 #<=> Ruta #> 0,
Sisgeo #= Indicaciones,
Indicaciones #= Visual,
Indicaciones #>= Auditivo,
Indicaciones #>= Vibratorio,
Mallavial #= Tramovial ,


0 #=<  Auditivo + Vibratorio,
 Auditivo + Vibratorio #=<  Indicaciones*2,



append(Features, Attr, S),

fd_labeling(S).
















