ALTER PROCEDURE [dbo].[uspGetUsers]
 @name nvarchar(50) = NULL

AS
BEGIN
    SET NOCOUNT ON;
	SELECT FirstName,LastName,Email
	FROM [dbo].Users
	WHERE (Firstname LIKE '%' + @name + '%' OR Lastname LIKE '%' + @name + '%' OR Email LIKE '%' + @name + '%') OR @name = '' OR @name IS NULL
END