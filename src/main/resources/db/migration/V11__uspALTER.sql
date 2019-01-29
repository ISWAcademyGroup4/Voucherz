ALTER PROCEDURE [dbo].[uspFindUserByToken3]
	-- Add the parameters for the stored procedure here
	@token NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT [Firstname],[Lastname],[Email],[Password],[PhoneNumber],[CompanySize],[Role] FROM Users WHERE Email = (SELECT Email FROM PasswordReset WHERE ResetToken = @token)
	DELETE PasswordReset WHERE ResetToken = @token
END