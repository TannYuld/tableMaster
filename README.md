# tableMaster

It is a basic table creator and reader. That created in **Java**. You can create basic string based tables with this program.

## Commands


| Command       | Command Syntax  | Description  |
| ------------- |:-------------:| :-----|
| Help          | `help`          | Print all the commands |
| Create table  | `create-table`  | Creates a new table    |
| Enter table   | ``choose-table``| Choose table from list |
| Exit table    | ``exit-table``  | Exits table so you can choose another table from disk |
| List table    | ``list-tables`` | List all the tables in the disk |
| Print table   | ``print-table`` | Prints and shows content of the selected table |
| Delete table  | ``delet-table`` | Deletes the selected table from the disk |
| Add entry     | ``add-entry``   | Adds a new line to selected table |
| Remove entry  | ``remove-at``   | Removes the desired lines of table |
| Change entry  | ``change-at``   | Changes values of the desirted entry |
| Find entry    | ``find-at``     | Finds the entry at desired index |
| Find entry value| ``find-val``  | Gets and prints the all entries with desired values at desired row |
| Rename row    | ``rename-row``  | Renames the desired row |
| Rename table  | ``rename-table``| Renames the selected table |

## Example

Creating a table like this bellow is super easy.

#### Table: Personal List

| Name | Personal Code | Gender | Birthday |
| :--: | :------------: | :-----: | :------: |
| Audley Valary | 023812434 | Male | 04/11/1997 |
| Kaitlyn Reese | 012347844 | Female | 24/03/1995 |
| Helen Freida  | 018346737 | Non-Binary | 13/01/2001 |

First create a table named Personal List

![image](https://user-images.githubusercontent.com/86157542/222253606-286e4cd4-f028-4375-8e89-87ed8761bc85.png)

![image](https://user-images.githubusercontent.com/86157542/222253806-b37ed226-79dc-4e12-bfbb-42096502e2a3.png)

Then set the desired number of rows

![image](https://user-images.githubusercontent.com/86157542/222255432-60e405bc-dcad-4017-b6e4-c25dfc9a3fb8.png)

After giving your rows names your table creates and saves automatically disk

![image](https://user-images.githubusercontent.com/86157542/222255694-f8a97954-ae0c-4014-8d8a-60686e1bc6c8.png)

After that you can control the layour of your table via printing it

![image](https://user-images.githubusercontent.com/86157542/222256187-d92a3918-cf1b-4c52-8a50-b43d13c1e433.png)

Then via ``add-entry`` command you can add personals to the table

In the end your table **Personal List** should be looking like that

![image](https://user-images.githubusercontent.com/86157542/222256858-7f628333-e0b2-4100-ae0d-ee6d346c6041.png)

[***After every operation table auto-save itself***]
