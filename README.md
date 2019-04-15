C:\Program Files\MongoDB\Server\3.4\bin
db = connect("localhost:27017/ProjectManagement")
db.sequence.insert({ name : "user_counter", sequence : 1})
db.sequence.insert({ name : "project_counter", sequence : 1})
db.sequence.insert({ name : "task_counter", sequence : 1})
db.sequence.insert({ name : "parent_task_counter", sequence : 1})