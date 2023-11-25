BEGIN
DECLARE @count smallint
SET @count = (select count(*) from [user] where username = 'SPADMIN')
if (@count < 1)
BEGIN

SET IDENTITY_INSERT [user] ON

INSERT INTO [user] (user_id, email, full_name, logged_in, password, role_type, username)
VALUES ('','admin@admin.com', 'SPADMIN', 0, '$2a$10$cSqKGvZvEGEzQhRFRyDVyuCR3Lf0e7FcpIfxd/0t5IOG9U.3flG8m', 0,
                    'SPADMIN')

SET IDENTITY_INSERT [user] OFF
END
END;
