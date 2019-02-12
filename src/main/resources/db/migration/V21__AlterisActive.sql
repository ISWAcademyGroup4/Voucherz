ALTER PROCEDURE [dbo].[uspIsActive]
	-- Add the parameters for the stored procedure here
	@active BIT,
	@email Nvarchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for procedure here
	UPDATE Users
	SET Active = @active WHERE Email = @email
END