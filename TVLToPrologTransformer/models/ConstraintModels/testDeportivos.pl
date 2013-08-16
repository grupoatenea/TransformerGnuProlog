productline(Features, Attributes):-

Features = [Vehicle, Vehicle_Car, Vehicle_Car_Home, Vehicle_Car_Family, Vehicle_Car_Sports, Vehicle_Car_Sports_Porsche, Vehicle_Car_Sports_Chevrolet, Vehicle_Car_Sports_Ferrari],

Attributes = [Vehicle_Car_Sports_Porsche_surfaceCuis, Vehicle_Car_Sports_Chevrolet_surfaceCuis, Vehicle_Car_Sports_Ferrari_surfaceCuis],

fd_domain([Vehicle, Vehicle_Car, Vehicle_Car_Home, Vehicle_Car_Family, Vehicle_Car_Sports, Vehicle_Car_Sports_Porsche, Vehicle_Car_Sports_Chevrolet, Vehicle_Car_Sports_Ferrari], 0, 1),

Vehicle_Car_Sports_Porsche #> 0 #<=> Vehicle_Car_Sports_Porsche_surfaceCuis #> 0,
Vehicle_Car_Sports_Chevrolet #> 0 #<=> Vehicle_Car_Sports_Chevrolet_surfaceCuis #> 0,
Vehicle_Car_Sports_Ferrari #> 0 #<=> Vehicle_Car_Sports_Ferrari_surfaceCuis #> 0,

Vehicle #= 1,
Vehicle_Car #= 1,
Vehicle_Car_Home + Vehicle_Car_Family + Vehicle_Car_Sports #= 1,
Vehicle_Car_Sports_Porsche + Vehicle_Car_Sports_Chevrolet + Vehicle_Car_Sports_Ferrari #= 1,

Vehicle_Car_Sports_Ferrari_surfaceCuis #>= 0,
Vehicle_Car_Sports_Chevrolet_surfaceCuis #>= 0,
Vehicle_Car_Sports_Porsche_surfaceCuis #>= 0,

append(Features, Attributes, S),
fd_labeling(S).

