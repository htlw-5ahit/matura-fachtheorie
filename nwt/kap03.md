# 3. Switch VLAN Konfiguration

a. VLAN Datenbank

```
s1(config)#vlan 10
s1(config-vlan)#name startup
s1(config-vlan)#exit
```

b. tagged ports

```
Switch(config)#int g0/1
Switch(config-if)#switchport mode trunk
Switch(config-if)#
```

c. untagged ports

```
s1(config)#int range fa0/13-24
s1(config-if-range)#switchport mode access
s1(config-if-range)#switchport access vlan 10
s1(config-if-range)#exit
```

d. IP Adresse

```
Router(config-subif)#encapsulation dot1Q 10
Router(config-subif)#ip address 192.168.10.1 255.255.255.0
Router(config-subif)#exit
```
```
Router(config)#int g0/0.20
Router(config-subif)#encapsulation dot1Q 20
Router(config-subif)#ip address 192.168.20.1 255.255.255.0
Router(config-subif)#exit
```
