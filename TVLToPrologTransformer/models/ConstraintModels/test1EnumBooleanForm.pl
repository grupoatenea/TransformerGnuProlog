productline(Features, Attributes):-

Features = [Car, Car_Alpharomeo, Car_Alpharomeo_Volkswagen, Car_Alpharomeo_Porsche, Car_Alpharomeo_Citroen, Car_Alpharomeo_Renault, Car_Ford, Car_Ford_Mazda, Car_Ford_Susuki, Car_Ford_Toyota, Car_Ford_Volvo, Car_Ford_Mercedes, Car_Nissan, Car_Nissan_Audi, Car_Nissan_Audi_BMW3, Car_Nissan_Audi_BMW2, Car_Nissan_Audi_BMW1, Car_Nissan_Chevrolet],

Attributes = [Car_mainColor, Car_optionnalColor, Car_Ford_size],

fd_domain([Car, Car_Alpharomeo, Car_Alpharomeo_Volkswagen, Car_Alpharomeo_Porsche, Car_Alpharomeo_Citroen, Car_Alpharomeo_Renault, Car_Ford, Car_Ford_Mazda, Car_Ford_Susuki, Car_Ford_Toyota, Car_Ford_Volvo, Car_Ford_Mercedes, Car_Nissan, Car_Nissan_Audi, Car_Nissan_Audi_BMW3, Car_Nissan_Audi_BMW2, Car_Nissan_Audi_BMW1, Car_Nissan_Chevrolet], 0, 1),

% Car_optionnalColor in [Red = 1, Blue = 2, White = 3]
fd_domain([Car_optionnalColor], 1, 3),
Car #> 0 #<=> Car_optionnalColor #> 0,
% Car_mainColor in [Red = 1, Blue = 2, White = 3]
fd_domain([Car_mainColor], 1, 3),
Car #> 0 #<=> Car_mainColor #> 0,
Car_Ford #> 0 #<=> Car_Ford_size #> 0,

Car #= 1,
Car_Alpharomeo + Car_Ford + Car_Nissan #= 1,
Car_Alpharomeo_Volkswagen + Car_Alpharomeo_Porsche + Car_Alpharomeo_Citroen + Car_Alpharomeo_Renault #>= 1 * Car_Alpharomeo,
Car_Alpharomeo_Volkswagen + Car_Alpharomeo_Porsche + Car_Alpharomeo_Citroen + Car_Alpharomeo_Renault #=< 4 * Car_Alpharomeo,
Car_Ford_Mazda + Car_Ford_Susuki + Car_Ford_Toyota + Car_Ford_Volvo + Car_Ford_Mercedes #>= 1 * Car_Ford,
Car_Ford_Mazda + Car_Ford_Susuki + Car_Ford_Toyota + Car_Ford_Volvo + Car_Ford_Mercedes #=< 5 * Car_Ford,
Car_Nissan #= Car_Nissan_Audi,
Car_Nissan #>= Car_Nissan_Chevrolet,
Car_Nissan_Audi_BMW3 + Car_Nissan_Audi_BMW2 + Car_Nissan_Audi_BMW1 #>= 0 * Car_Nissan_Audi,
Car_Nissan_Audi_BMW3 + Car_Nissan_Audi_BMW2 + Car_Nissan_Audi_BMW1 #=< 3 * Car_Nissan_Audi,

Car_mainColor #\= Car_optionnalColor,
Car_Nissan #==> Car_Ford,
Car_Ford_size #> 0 #==> Car_Ford_size #= 100,

append(Features, Attributes, S),
fd_labeling(S).

