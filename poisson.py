import scipy.special
import numpy as np

def poisson(p_lambda):
    assert(p_lambda > 0)
    k = []
    p = []
    k_max = 20
    for i in range(k_max):
        k.append(i)
        p.append(np.exp(k[i]*np.log(p_lambda) - p_lambda - scipy.special.gammaln(k[i]+1)))
    ofile = open("Poisson_theory_" + str(p_lambda) + ".txt", "w")
    for i in range(len(k)):
        ofile.write(str(k[i]) + "   " + str(p[i]) + "\n")
    ofile.close()

def main():
    import sys
    import os
    if (len(sys.argv) != 2):
        print "l = argv[1]. "
        return -1

    l = float(sys.argv[1])
    poissonParameter = 1.0
    while(poissonParameter <= l):
        poisson(poissonParameter)
        print poissonParameter
        poissonParameter = poissonParameter + 1
    return 0

if __name__ == "__main__":
    import sys
    sys.exit(main())
