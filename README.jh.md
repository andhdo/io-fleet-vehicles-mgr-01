# alternative way to generate application
we can generate the application using jhipster
* prerrequisites
  - node-17.1.0
* install generator & dependencies
```
sudo npm i -g generator-jhipster
sudo npm i -g rimraf
```
* create the folder for application

```
cd ~/.apps/e_io.Apps
export PROJECT_NAME=io-fleet-vehicles-mgr-jh1
mkdir $PROJECT_NAME && cd $PROJECT_NAME
```
* execute the generator
```
jhipster

	type: monolithic
	basename: fleet_vehicles_mgr
	default package io.fleet_vehicles_mgr
	auth: jwt-auth
	database: mongo
	cache: none
	building: gradle
	jh-registry (monitor & scale): no 
	other tech: (no) 
	clientside: react
	admin-ui: yes
	theme: default jhipster (booswatch)
	i18n: yes
	native lang: english
	additional: spanish
	unittest additional to junit & jest: no
	other generators: no
```

* add the entity, resource, service, controller
`jhipster import-jdl ./_io-fleet.jdl`
	
* run app

    `./gradlew`


