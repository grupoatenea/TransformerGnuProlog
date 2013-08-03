productline(L):-
L=[SmartDeliv,F1,F5,F6,F2,F3,F8,F9],
fd_domain([SmartDeliv,F1,F5,F6,F2,F3,F8,F9], 0, 1),
SmartDeliv#=1,
SmartDeliv#<=>F1,
SmartDeliv#<=>F6,
SmartDeliv#<=>F8,
(1-F5)+F9#>0,
fd_labeling(L).
