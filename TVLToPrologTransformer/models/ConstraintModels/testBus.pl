productline(Features, Attributes):-

Features = [Bus, Bus_Volkswagen, Bus_Mercedes],

Attributes = [Bus_Volkswagen_weight, Bus_Volkswagen_price, Bus_Mercedes_a, Bus_Mercedes_b, Bus_Mercedes_weight, Bus_Mercedes_price],

fd_domain([Bus, Bus_Volkswagen, Bus_Mercedes], 0, 1),

Bus_Volkswagen #> 0 #<=> Bus_Volkswagen_price #> 0,
Bus_Volkswagen #> 0 #<=> Bus_Volkswagen_weight #> 0,
Bus_Mercedes #> 0 #<=> Bus_Mercedes_price #> 0,
Bus_Mercedes #> 0 #<=> Bus_Mercedes_weight #> 0,
Bus_Mercedes #> 0 #<=> Bus_Mercedes_b #> 0,
Bus_Mercedes #> 0 #<=> Bus_Mercedes_a #> 0,

Bus #= 1,
Bus_Volkswagen + Bus_Mercedes #= 1,

Bus_Mercedes_a #==> Bus_Mercedes_price #< 180000,
Bus_Mercedes_b #> 0 #==> Bus_Mercedes_b #= R1 + R2 + R3,
R1 #= 4,
R2 #= 8,
R3 #= 9,
Bus_Mercedes_weight #> 0 #==> Bus_Mercedes_weight #= 75,
Bus_Mercedes_price #> 0 #==> Bus_Mercedes_price #= 170000,
Bus_Volkswagen_weight #> 0 #==> Bus_Volkswagen_weight #= 8,
Bus_Volkswagen_price #> 0 #==> Bus_Volkswagen_price #= 180000,

append(Features, Attributes, S),
fd_labeling(S).

