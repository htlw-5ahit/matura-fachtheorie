# 4. Network Address Translation konfigurieren

a. Statisches NAT

```
ISP(config)#int g0/1
ISP(config-if)#ip nat out
ISP(config-if)#exit
ISP(config)#int g0/0
ISP(config-if)#ip nat inside
ISP(config-if)#exit
```

```
ISP(config)#ip nat inside source static 192.168.20.10 100.100.100.100
```

```
ISP#show ip nat tr
Pro  Inside global     Inside local       Outside local      Outside global
---  100.100.100.100   192.168.20.10      ---                ---
```

b. NAT overload mit PAT

```
ISP(config)#access-list 10 permit 192.168.10.0 255.255.255.0 
ISP(config)#ip nat inside source list 10 int g0/1 overload
```
