import numpy
import matplotlib.pyplot as plt

# for y in range (1,3):
# 	for x in range(0,7):
# 		print '[',x,',', y*3,']'

points = numpy.array([[ 0 , 3 ],[ 1 , 3 ],[ 2 , 3 ],[ 3 , 3 ],[ 4 , 3 ],[ 5 , 3 ],[ 6 , 3 ],[ 0 , 6 ],[ 1 , 6 ],[ 2 , 6 ],[ 3 , 6 ],[ 4 , 6 ],[ 5 , 6 ],[ 6 , 6 ]])
# edges = numpy.array([[0,1],[3,4],[3,2],[2,4]])

x = points[:,0].flatten()
y = points[:,1].flatten()

plt.plot(x[edges.T], y[edges.T], linestyle='-', color='y',
        markerfacecolor='red', marker='o') 

plt.show()