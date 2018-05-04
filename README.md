# Example of maze solver on java using Lee method


### Input format

```$xslt
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
```

```$xslt
sizeX  sizeY
startX startY
endX   endY
```

and matrix where `1` is a wall and `0` is a free tile.

### Output

```$xslt
##########
#Sxx     #
# #x######
# #xx    #
# ##x# ###
# # x# # #
# # xx   #
# ###x####
# #  xxxE#
##########

```

`S` - start 
`E` - end 
`x` - path 
`#` - wall
` ` - free space