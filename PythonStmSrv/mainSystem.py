from server import ServerStm
import sys

if __name__ == "__main__":
    #while True:
    if len(sys.argv) > 3:
        ServerStm(sys.argv[1], sys.argv[2], sys.argv[3])
    else:
        ServerStm("localhost", "myRequest", "myResponse")
