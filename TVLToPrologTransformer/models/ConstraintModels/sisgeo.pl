productline(Features, Attributes):-

Features = [Sisgeo, Sisgeo_Indicaciones, Sisgeo_Indicaciones_Vibratorio, Sisgeo_Indicaciones_Auditivo, Sisgeo_Indicaciones_Visual, Sisgeo_Ubicacion, Sisgeo_Mapa, Sisgeo_Mapa_Capas, Sisgeo_Mapa_Capas_Mallavial, Sisgeo_Mapa_Capas_Mallavial_Tramovial, Sisgeo_Mapa_Capas_Trafico, Sisgeo_Mapa_Capas_Puntosinteres, Sisgeo_Mapa_Capas_Ruta, Sisgeo_Mapa_Capas_Relieve, Sisgeo_Mapa_Capas_Aplicaciones, Sisgeo_Mapa_Capas_Satelite, Sisgeo_Mapa_Zonas],

Attributes = [Sisgeo_Ubicacion_coordenadas, Sisgeo_Ubicacion_precision, Sisgeo_Mapa_Capas_Ruta_opcionesruta, Sisgeo_Mapa_Capas_Ruta_mediostransporte],

fd_domain([Sisgeo, Sisgeo_Indicaciones, Sisgeo_Indicaciones_Vibratorio, Sisgeo_Indicaciones_Auditivo, Sisgeo_Indicaciones_Visual, Sisgeo_Ubicacion, Sisgeo_Mapa, Sisgeo_Mapa_Capas, Sisgeo_Mapa_Capas_Mallavial, Sisgeo_Mapa_Capas_Mallavial_Tramovial, Sisgeo_Mapa_Capas_Trafico, Sisgeo_Mapa_Capas_Puntosinteres, Sisgeo_Mapa_Capas_Ruta, Sisgeo_Mapa_Capas_Relieve, Sisgeo_Mapa_Capas_Aplicaciones, Sisgeo_Mapa_Capas_Satelite, Sisgeo_Mapa_Zonas], 0, 1),

Sisgeo_Ubicacion #> 0 #<=> Sisgeo_Ubicacion_precision #> 0,
% Sisgeo_Ubicacion_coordenadas in [lineales = 1, polares = 2]
fd_domain([Sisgeo_Ubicacion_coordenadas], 1, 2),
Sisgeo_Ubicacion #> 0 #<=> Sisgeo_Ubicacion_coordenadas #> 0,
% Sisgeo_Mapa_Capas_Ruta_mediostransporte in [persona = 1, vehiculo = 2, bus = 3, metro = 4, bicicleta = 5]
fd_domain([Sisgeo_Mapa_Capas_Ruta_mediostransporte], 1, 5),
Sisgeo_Mapa_Capas_Ruta #> 0 #<=> Sisgeo_Mapa_Capas_Ruta_mediostransporte #> 0,
% Sisgeo_Mapa_Capas_Ruta_opcionesruta in [mas_corta = 1, mas_rapida = 2, optima = 3, sin_peajes = 4, sin_trafico = 5, autopistas = 6]
fd_domain([Sisgeo_Mapa_Capas_Ruta_opcionesruta], 1, 6),
Sisgeo_Mapa_Capas_Ruta #> 0 #<=> Sisgeo_Mapa_Capas_Ruta_opcionesruta #> 0,

Sisgeo #= 1,
Sisgeo #= Sisgeo_Indicaciones,
Sisgeo #= Sisgeo_Ubicacion,
Sisgeo #>= Sisgeo_Mapa,
Sisgeo_Indicaciones #>= Sisgeo_Indicaciones_Vibratorio,
Sisgeo_Indicaciones #>= Sisgeo_Indicaciones_Auditivo,
Sisgeo_Indicaciones #= Sisgeo_Indicaciones_Visual,
Sisgeo_Mapa #= Sisgeo_Mapa_Capas,
Sisgeo_Mapa #>= Sisgeo_Mapa_Zonas,
Sisgeo_Mapa_Capas_Mallavial + Sisgeo_Mapa_Capas_Trafico + Sisgeo_Mapa_Capas_Ruta + Sisgeo_Mapa_Capas_Puntosinteres + Sisgeo_Mapa_Capas_Relieve + Sisgeo_Mapa_Capas_Aplicaciones + Sisgeo_Mapa_Capas_Satelite #>= 1 * Sisgeo_Mapa_Capas,
Sisgeo_Mapa_Capas_Mallavial + Sisgeo_Mapa_Capas_Trafico + Sisgeo_Mapa_Capas_Ruta + Sisgeo_Mapa_Capas_Puntosinteres + Sisgeo_Mapa_Capas_Relieve + Sisgeo_Mapa_Capas_Aplicaciones + Sisgeo_Mapa_Capas_Satelite #=< 6 * Sisgeo_Mapa_Capas,
Sisgeo_Mapa_Capas_Mallavial_Tramovial #= 1,

Sisgeo_Mapa_Capas_Relieve + Sisgeo_Mapa_Capas_Satelite #=< 1,
Sisgeo_Ubicacion_precision #>= 1, Sisgeo_Ubicacion_precision #=< 7,

append(Features, Attributes, S),
fd_labeling(S).

