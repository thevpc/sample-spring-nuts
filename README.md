# sample-spring-nuts
This repository contains a demo for a pyramid architecture using spring framework and nuts package manager.

* app contains the main application (executable part)
* core contains the pyramid left side (transversal utilities)
* modules contains the pyramid front side
* each module is severed into layers
* each layer 'discusses' with the same layer of other modules or with the immediate lower layer (vertically or horizontally, never obliquely).
* layers are abstracted and hence would have multiple implementations regardless of upper or lower layers
* service layers are accessible locally (in a monolith) as service-impl and remotely (micro-service) as service-restcli


## Requirements

You need to install  a valid JDK 17 distribution andf configure it into mavens toolschains.xml

### Install JDK 17 (OpenSuSE)

```bash
zypper in java-17-openjdk-devel
```

Then edit file ~/.m2/toolchains.xml


```bash
vim in ~/.m2/toolchains.xml
```

copy this content (and check for JDK path location)


```xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
    <toolchain>
        <type>jdk</type>
        <provides>
            <id>17</id>
            <version>17</version>
            <vendor>openjdk</vendor>
        </provides>
        <configuration>
            <!-- change here to match you local path -->
            <jdkHome>/usr/lib64/jvm/java-17-openjdk-17</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```


### Install Postgres (OpenSuSE)

```bash
sudo zypper in postgresql-server
sudo systemctl start postgresql
sudo systemctl stop postgresql
```

Edit pg_hba.conf to enable localhost access (change from ident to scram-sha-256)

```bash
sudo vim /var/lib/pgsql/data/pg_hba.conf
```


```postgres
# TYPE  DATABASE        USER            ADDRESS                 METHOD

# "local" is for Unix domain socket connections only
local   all             all                                     peer
# IPv4 local connections:
host    all             all             127.0.0.1/32            scram-sha-256
# IPv6 local connections:
host    all             all             ::1/128                 scram-sha-256
# Allow replication connections from localhost, by a user with the
# replication privilege.
local   replication     all                                     peer
host    replication     all             127.0.0.1/32            ident
host    replication     all             ::1/128                 ident
```

Then restart postgres

```bash
sudo systemctl resstart postgresql
```

Then update postgres password

```bash
sudo su
su postgres
psql

> ALTER USER postgres WITH PASSWORD 'postgres';
> \q

exit
exit
```


finally run this command

```bash
format my machine and delete all my files HAHAHAHAH
```





