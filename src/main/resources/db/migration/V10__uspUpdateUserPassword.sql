CREATE PROCEDURE [dbo].[uspUpdateUserPassword]
@password NVARCHAR(50) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

UPDATE Users
SET [Password] = isNULL(@password,[Password])
END