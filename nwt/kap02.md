# 2. Router Interface Konfiguration

a. IPv4 Adresse + Subnet Mask

```
conf t
int g0/0
description Netzwerk links
ip address 192.168.10.254 255.255.255.0
no shutdown
```

b. Clock Rate

```
int s0/1/0
description Router-Verbindung
ip address 172.17.25.10 255.255.255.252
clock rate 128000 (nur eine Seite...)
no shutdown
end
```
