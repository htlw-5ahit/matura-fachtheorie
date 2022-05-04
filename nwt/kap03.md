# 3. Switch VLAN Konfiguration

a. VLAN Datenbank

```
s1(config)#vlan 10
s1(config-vlan)#name startup
s1(config-vlan)#exit
```

b. tagged ports

c. untagged ports

```
s1(config)#int range fa0/13-24
s1(config-if-range)#switchport mode access
s1(config-if-range)#switchport access vlan 10
s1(config-if-range)#exit
```

d. IP Adresse
