# 1. Grundkonfiguration eines Cisco Switches oder Routers

a. Hostname

```
enable
config term
hostname r2
```

b. Message of the day

```
banner motd Cisco von Clemens
```

c. Enable password

```
enable password cisco
```

d. console password

```
line con 0
password cisco
login
exit
```

e. telnet password

```
line vty 0 15
password cisco
login
exit
```

f. password encryption

```
service password-encryption
```

---

```
no ip domain-lookup
```

```
copy running-config startup-config
```
