import math
import turtle as tr 

tr.bgcolor("black")
tr.shape("turtle")
tr.speed(0)

phi = 137.7*(math.pi/180)

#Petalos
h = 0
for i in range(16):
    for j in range(18):
        tr.color("yellow")
        h+=0.005
        tr.rt(90)
        tr.circle(160-j*6,90)
        tr.lt(90)
        tr.circle(160-j*6,90)
        tr.rt(180)
    tr.circle(40,24)

#Centro
tr.fillcolor("brown")
tr.color("brown")


for z in range(160):
    r=4*math.sqrt(z)
    theta = z*phi
    x = r*math.cos(theta)
    y = r*math.sin(theta)
    tr.penup()
    tr.setheading(z*137.508)
    tr.goto(x,y+50)
    tr.setheading(z*137.508)
    tr.pendown()
    tr.stamp()
    
tr.done()



