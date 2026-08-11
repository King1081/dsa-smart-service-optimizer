# Ghana Smart Service Operations Optimizer
### DCIT 204/308 Joint Group Project — University Campus Service Hub

## Setup

Requires Java 17+ and Maven.

```bash
mvn compile          # build everything
mvn test             # run all unit tests
mvn exec:java         # run Main.java (loads the dataset, builds the graph)
```

If your machine doesn't have Maven installed, most IDEs (IntelliJ, VS Code
with the Java extension) can import this folder as a Maven project and run
it without a manual install.

## Project layout

```
data/                                  the 4 shared CSVs + team_parameters.csv
src/main/java/com/ug/dsaproject/
  model/                               Location, Road, Resource, ServiceRequest, TeamParameter
  util/CsvLoader.java                  loads all 5 CSVs — use this, don't re-parse yourself
  datastructures/
    linkedlist/       Josephine        RequestLinkedList
    stack/             Marie-Anne       RouteStack
    queue/              Joseph           RequestQueue
    priorityqueue/  Vical            UrgentRequestHeap
    bst/                    Marfo            LocationBST
    hashtable/        Khalid           ResourceHashTable
    graph/                (shared)        CampusGraph — used by all 4 graph-role members
    disjointset/       Akubia           UnionFind
  algorithms/
    dijkstra/           Darko & Anna     DijkstraShortestPath
    mst/                    Papah & Abel     CampusMST (Kruskal + Prim)
  db/                       Nigel            SchemaSetup
  Main.java                                    entry point / sanity check
src/test/java/...                      mirrors src/main, one test class per module
```

## Workflow

1. **Pull this whole folder** — everyone works from the same package layout
   so nobody's code conflicts when merging.
2. **Find your module** using the table above. Your class already has:
   - the correct package and imports
   - method signatures matching what the rest of the project expects
   - a Javadoc comment explaining what to build and how it's used elsewhere
   - `TODO` markers where your implementation goes
3. **Do not rename classes, packages, or method signatures** without telling
   the group — other people's code (and Main.java) calls into your module by
   these exact names.
4. **Use your row from `data/team_parameters.csv`** as input where your
   module calls for a derived parameter (seed, weight, penalty, table size).
5. **Write your own tests** in the matching `src/test/...` file — each stub
   already has 1 passing test and 2-3 TODO tests. Aim for 3-4 per person.
6. **Keep a trace table** as you build — a hand-worked example run of your
   algorithm on a small slice of the data, showing state at each step. You
   need this for the report and defense regardless of whether your code
   works perfectly yet.
7. Graph-role members (Darko, Anna, Papah, Abel): you all build on top of
   the same `CampusGraph` — agree on any changes to it as a group before
   pushing.

## Data

All 4 CSVs in `data/` follow the original template headers:
- `locations.csv` — 50 rows
- `roads.csv` — 100 rows, verified fully connected
- `resources.csv` — 30 rows
- `service_requests.csv` — 300 rows

Don't hand-edit these without telling the group — algorithms and trace
tables assume this exact dataset.

## Still outstanding (not in this skeleton yet)

- DB persistence layer (Nigel — schema is drafted as a comment in
  `SchemaSetup.java`, actual JDBC code still to do)
- Performance graphs (once algorithms are implemented, benchmark against
  varying input sizes)
- Final technical report and defense prep
