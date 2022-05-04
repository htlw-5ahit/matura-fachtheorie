# 5. Routing Protokolle einrichten

a. Statisch IPv4

```
ip route 192.168.20.0 255.255.255.0 172.17.25.10
ip route 192.168.10.0 255.255.255.0 172.17.25.9
```

b. Dynamisch mit RIPv2

```
r1(config)#router rip
r1(config-router)#version 2
r1(config-router)#network 192.168.10.0
r1(config-router)#network 172.17.25.8
r1(config-router)#exit
```

```
r1#show ip route
Codes: L - local, C - connected, S - static, R - RIP, M - mobile, B - BGP
       D - EIGRP, EX - EIGRP external, O - OSPF, IA - OSPF inter area
       N1 - OSPF NSSA external type 1, N2 - OSPF NSSA external type 2
       E1 - OSPF external type 1, E2 - OSPF external type 2, E - EGP
       i - IS-IS, L1 - IS-IS level-1, L2 - IS-IS level-2, ia - IS-IS inter area
       * - candidate default, U - per-user static route, o - ODR
       P - periodic downloaded static route

Gateway of last resort is not set

     172.17.0.0/16 is variably subnetted, 2 subnets, 2 masks
C       172.17.25.8/30 is directly connected, Serial0/1/0
L       172.17.25.9/32 is directly connected, Serial0/1/0
     192.168.10.0/24 is variably subnetted, 2 subnets, 2 masks
C       192.168.10.0/24 is directly connected, GigabitEthernet0/0
L       192.168.10.254/32 is directly connected, GigabitEthernet0/0
R    192.168.20.0/24 [120/1] via 172.17.25.10, 00:00:17, Serial0/1/0
```

c. Default Route

```
ip route 0.0.0.0 0.0.0.0 172.17.10.254
```

```
r2#show ip route
Codes: L - local, C - connected, S - static, R - RIP, M - mobile, B - BGP
       D - EIGRP, EX - EIGRP external, O - OSPF, IA - OSPF inter area
       N1 - OSPF NSSA external type 1, N2 - OSPF NSSA external type 2
       E1 - OSPF external type 1, E2 - OSPF external type 2, E - EGP
       i - IS-IS, L1 - IS-IS level-1, L2 - IS-IS level-2, ia - IS-IS inter area
       * - candidate default, U - per-user static route, o - ODR
       P - periodic downloaded static route

Gateway of last resort is 172.17.10.254 to network 0.0.0.0

     172.17.0.0/16 is variably subnetted, 4 subnets, 3 masks
C       172.17.10.0/24 is directly connected, GigabitEthernet0/1
L       172.17.10.1/32 is directly connected, GigabitEthernet0/1
C       172.17.25.8/30 is directly connected, Serial0/1/0
L       172.17.25.10/32 is directly connected, Serial0/1/0
R    192.168.10.0/24 [120/1] via 172.17.25.9, 00:00:16, Serial0/1/0
     192.168.20.0/24 is variably subnetted, 2 subnets, 2 masks
C       192.168.20.0/24 is directly connected, GigabitEthernet0/0
L       192.168.20.254/32 is directly connected, GigabitEthernet0/0
S*   0.0.0.0/0 [1/0] via 172.17.10.254
```

```
r2(config)#router rip
r2(config-router)#default-information or
```

```
r1#show ip route
Codes: L - local, C - connected, S - static, R - RIP, M - mobile, B - BGP
       D - EIGRP, EX - EIGRP external, O - OSPF, IA - OSPF inter area
       N1 - OSPF NSSA external type 1, N2 - OSPF NSSA external type 2
       E1 - OSPF external type 1, E2 - OSPF external type 2, E - EGP
       i - IS-IS, L1 - IS-IS level-1, L2 - IS-IS level-2, ia - IS-IS inter area
       * - candidate default, U - per-user static route, o - ODR
       P - periodic downloaded static route

Gateway of last resort is 172.17.25.10 to network 0.0.0.0

     172.17.0.0/16 is variably subnetted, 3 subnets, 3 masks
R       172.17.10.0/24 [120/1] via 172.17.25.10, 00:00:09, Serial0/1/0
C       172.17.25.8/30 is directly connected, Serial0/1/0
L       172.17.25.9/32 is directly connected, Serial0/1/0
     192.168.10.0/24 is variably subnetted, 2 subnets, 2 masks
C       192.168.10.0/24 is directly connected, GigabitEthernet0/0
L       192.168.10.254/32 is directly connected, GigabitEthernet0/0
R    192.168.20.0/24 [120/1] via 172.17.25.10, 00:00:09, Serial0/1/0
R*   0.0.0.0/0 [120/1] via 172.17.25.10, 00:00:09, Serial0/1/0
```
