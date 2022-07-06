from client import ClientStm
import sys

if __name__ == "__main__":
    #while True:
    if len(sys.argv) > 3:
        ClientStm(sys.argv[1], sys.argv[2], sys.argv[3])
    else:
        ClientStm("localhost", "myRequest", "myResponse")
