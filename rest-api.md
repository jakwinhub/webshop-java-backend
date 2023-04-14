Bausteine um HTTP Endpoint zu bestimmen
- Pfad
- Query-Parameter
- Http Verb: GET, POST, PUT, DELETE (HEAD, OPTIONS, PATCH, TRACE)
- Request Body  (Daten werden darüber transportiert)

REST: Ressourcen
Produkte
Kunden
Bestellungen

Lade alle Produkte vom Server:
GET /api/products

Lade Produkte mit bestimmtem Tag
GET /api/products?tag={tag}

Lade Produkt mit bestimmter ID
DELETE /api/products/{id}

Erzeuge ein neues Produkt
POST /api/products

Update eine Produktbeschreibung
PUT /api/products/{id}/description

Bestelle ein Produkt  --> neue Bestellung
POST /api/orders

Füge Produkt zu einer Bestellung hinzu
PUT /api/orders/{id}/products

Füge Tags zu einem Produkt hinzu
PUT /api/products/{id}/tags

Update Produkt
PUT /api/products/{id}