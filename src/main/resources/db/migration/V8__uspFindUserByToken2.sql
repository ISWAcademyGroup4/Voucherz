CREATE PROCEDURE uspFindUserByToken2
	-- Add the parameters for the stored procedure here
	@resetToken NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT * FROM Users WHERE Email = (SELECT Email FROM PasswordReset WHERE ResetToken = @resetToken)
	--DELETE PasswordReset WHERE ResetToken = @activationToken
END
GO