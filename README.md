# sample-spring-nuts
This repository contains a demo for a pyramid architecture using spring framework and nuts package manager.

* app contains the main application (executable part)
* core contains the pyramid left side (transversal utilities)
* modules contains the pyramid front side
* each module is severed into layers
* each layer 'discusses' with the same layer of other modules or with the immediate lower layer (vertically or horizontally, never obliquely).
* layers are abstracted and hence would have multiple implementations regardless of upper or lower layers
* service layers are accessible locally (in a monolith) as service-impl and remotely (micro-service) as service-restcli