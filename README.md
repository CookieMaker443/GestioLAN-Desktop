## GestioLAN per client desktop ##

Questo software è un "pezzo" di un sistema piu grande, esso permette di visualizzare il contenuto del database MySQL "GestioLAN"

## Tecnologie utilizzate

JavaFX

## Come funziona?

# TODO
il software avrà una configurazione dove viene detto al software l'IP e la porta in cui si trova l'API per interfaccairsi al database. 
Questi valori potranno essere cambiati nella pagina di login.
Il login userà i dati conservati nel database in cui ti stai connettendo

Nella cartella "FXML" ci sono i file che gestiscono la struttura grafica delle pagine
Nella cartella "Controllers" ci sono i file che gestiscono la logica delle pagine
    Nota: ogni cartella dentro controller gestisce pagina, se dentro di essa ci sono altri
    oggetti, per una questione di ordine ho separato la responsabilità a piu classi, che pero
    sono delle sottocartelle del controller "principale"

