# 6. Access Control Listen konfigurieren

a. Accesslisten erstellen und den passenden Interfaces zuordnen

```
r2(config)#ip access-list standard 20
r2(config-std-nacl)#deny 192.168.10.0 0.0.0.255
r2(config-std-nacl)#permit any
r2(config-std-nacl)#exit
```

```
r2#show ac
Standard IP access list 20
    10 deny 192.168.10.0 0.0.0.255
    20 permit any
```

```
r2(config)#int g0/0
r2(config-if)#ip access-group 20 out
```
